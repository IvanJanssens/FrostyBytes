import {ActivityIndicator, FlatList, View} from "react-native";
import { Alert } from 'react-native';
import {styles} from "./categoriesScreen.styles";
import CardItem from "../../components/cardItem/CardItem";
import {faHouse} from "@fortawesome/free-solid-svg-icons";
import {gql, useSubscription} from "@apollo/client";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import React from "react";
import {Snackbar} from "react-native-paper";


const FETCH_CATEGORIES = gql`
    subscription getCategories {
        getCategories {
            id
            name
        }
    }
`;
export const CategoriesScreen = () => {

    const {data, error, loading} = useSubscription(
        FETCH_CATEGORIES,
        {variables: {page: 0, pageSize: 10}}
    );
    if (loading) return (<ActivityIndicator color={frostyBytesTheme.colors.secondary['500']}></ActivityIndicator>)
    return (<View style={styles.container}>
        <FlatList style={styles.listContainer}
                  data={data?.getFridges}
                  renderItem={({item}) => <View style={styles.listItem}>
                      <CardItem icon={faHouse} title={item.name}/>
                  </View>
                  }
                  keyExtractor={(item) => item.id.toString()}
        />
    </View>)
}


