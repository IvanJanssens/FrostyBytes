import {ActivityIndicator, FlatList, View} from "react-native";
import {styles} from "./categoriesScreen.styles";
import CardItem from "../../components/cardItem/CardItem";
import {faHouse} from "@fortawesome/free-solid-svg-icons";
import {gql, useMutation, useSubscription} from "@apollo/client";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import React from "react";


const FETCH_CATEGORIES = gql`
    subscription getCategories {
        getCategories {
            id
            name
        }
    }
`;


const DELETE_CATEGORY = gql`
    mutation deleteCategory($id:ID!) {
        deleteCategory(id:$id) 
    }
`;
export const CategoriesScreen = () => {
    const [deleteMutation, deleteMutationStatus] = useMutation(
        DELETE_CATEGORY
    );
    const subscriptionResult = useSubscription(
        FETCH_CATEGORIES,
        {variables: {page: 0, pageSize: 10}}
    );
    if (subscriptionResult.loading || deleteMutationStatus.loading) return (<ActivityIndicator color={frostyBytesTheme.colors.secondary['500']}></ActivityIndicator>)
    return (<View style={styles.container}>
        <FlatList style={styles.listContainer}
                  data={subscriptionResult.data?.getFridges}
                  renderItem={({item}) => <View style={styles.listItem}>
                      <CardItem icon={faHouse} title={item.name} onDeletePress={()=>deleteMutation(item.id) }/>
                  </View>
                  }
                  keyExtractor={(item) => item.id.toString()}
        />
    </View>)
}


