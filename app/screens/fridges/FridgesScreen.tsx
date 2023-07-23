import {ActivityIndicator, FlatList, View} from "react-native";
import {styles} from "./fridgesScreen.styles";
import CardItem from "../../components/cardItem/CardItem";
import {faHouse} from "@fortawesome/free-solid-svg-icons";
import {gql, useMutation, useSubscription} from "@apollo/client";
import {withApollo} from "@apollo/client/react/hoc";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import React from "react";


const DELETE_CATEGORY = gql`
    mutation deleteFridge($id:ID!) {
        deleteFridge(id:$id)
    }
`;
const FETCH_FRIDGES = gql`
    subscription getFridges($page: Int!, $pageSize: Int!) {
        getFridges(
            page: $page,
            pageSize: $pageSize
        ) {
            id
            name
        }
    }
`;

 const FridgesScreen = () => {
     const [deleteMutation, deleteMutationStatus] = useMutation(
         DELETE_CATEGORY
     );
    const  subscriptionResult = useSubscription(
        FETCH_FRIDGES,
        {variables: {page: 0,pageSize:10}}
    );

     if (subscriptionResult.loading || deleteMutationStatus.loading) return (<ActivityIndicator color={frostyBytesTheme.colors.secondary['500']}></ActivityIndicator>)


     return (
        <View style={styles.container}>
                <FlatList style={styles.listContainer}
                    data={subscriptionResult.data?.getFridges}
                    renderItem={({item}) => <View  style={styles.listItem}>
                        <CardItem icon={faHouse} title={item.name} onDeletePress={() => deleteMutation({
                            variables: {id: item.id.toString()},
                        })} />
                    </View>
                    }
                    keyExtractor={(item) => item.id.toString()}
                />
        </View>
)}

export default withApollo(FridgesScreen);

