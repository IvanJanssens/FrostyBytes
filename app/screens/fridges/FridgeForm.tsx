import {FlatList, View} from "react-native";
import {styles} from "./fridgesScreen.styles";
import CardItem from "../../components/cardItem/CardItem";
import {faHouse} from "@fortawesome/free-solid-svg-icons";
import {gql, useSubscription} from "@apollo/client";
import {withApollo} from "@apollo/client/react/hoc";


export const FETCH_FRIDGES = gql`
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
    const { data, error, loading } = useSubscription(
        FETCH_FRIDGES,
        {variables: {page: 0,pageSize:10}}
    );

    return (
        <View style={styles.container}>
                <FlatList style={styles.listContainer}
                    data={data?.getFridges}
                    renderItem={({item}) => <View  style={styles.listItem}>
                        <CardItem icon={faHouse} title={item.name}/>
                    </View>
                    }
                    keyExtractor={(item) => item.id.toString()}
                />
        </View>
)}

export default withApollo(FridgesScreen);

