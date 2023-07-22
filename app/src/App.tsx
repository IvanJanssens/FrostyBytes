import React from 'react';
import {Tabs} from "../components/navigation/Tabs";
import {NavigationContainer} from "@react-navigation/native";
import {ApolloProvider} from "@apollo/client";


const App = () => {
    return (
    <NavigationContainer>
        <Tabs/>
    </NavigationContainer>
    )
}

export default App;


