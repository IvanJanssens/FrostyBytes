import {Text, View} from "react-native";
import {styles} from "./homeScreen.styles"
import CardItem from "../../components/cardItem/CardItem";
import {faHouse} from "@fortawesome/free-solid-svg-icons";

export const HomeScreen = () => (
    <View style={styles.container}>
        <Text>Home Screen</Text>
        <CardItem icon={faHouse} />
    </View>
)
