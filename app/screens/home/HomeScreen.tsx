import {Image, View} from "react-native";
import {styles} from "./homeScreen.styles";


export const HomeScreen = () => {

    return (
        <View
            style={styles.container}>
            <Image source={require("../../assets/olaf.png")} style={styles.image}></Image>
        </View>
    );
}
