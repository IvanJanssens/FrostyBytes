import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {GraphQLWsLink} from "@apollo/client/link/subscriptions";
import {createClient} from "graphql-ws";
import {ApolloClient, ApolloProvider, HttpLink, InMemoryCache, split} from "@apollo/client";
import {getMainDefinition} from "@apollo/client/utilities";
import {PaperProvider} from "react-native-paper";
import {View} from "react-native";
import {frostyBytesTheme} from "../constants/frostyBytesTheme";

const wsLink = new GraphQLWsLink(createClient({
    url:"ws://localhost:8080/graphql"
}))

const httpLink = new HttpLink({
    uri:  "http://localhost:8080/graphql"
})

const splitLink = split(
    ({query}) => {
        const definition = getMainDefinition(query);
        return (
            definition.kind ==='OperationDefinition' && definition.operation ==='subscription'
        );
    },
    wsLink,
    httpLink
    )

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

const Client = new ApolloClient({
    link:splitLink,
    cache:new InMemoryCache()
})
root.render(
  <div style={{ display: 'flex', flex: 1, backgroundColor: frostyBytesTheme.colors.gray["50"]}}>
      <React.StrictMode>
          <ApolloProvider client={Client}>
              <PaperProvider>
                <App/>
              </PaperProvider>
          </ApolloProvider>
      </React.StrictMode>
  </div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
