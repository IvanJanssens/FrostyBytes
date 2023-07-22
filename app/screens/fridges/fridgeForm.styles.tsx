import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {StyleSheet} from "react-native";

export const styles = StyleSheet.create({
    container:{
        flex:1,
        alignItems: "stretch",
        justifyContent:'space-around',
        backgroundColor: frostyBytesTheme.colors.gray["50"],
        borderRadius: 35,
        padding: '3rem'
    },
    text: {
        color: frostyBytesTheme.colors.tertiary["400"],
    },
    inputField: {
        borderColor: frostyBytesTheme.colors.tertiary["400"],
        backgroundColor: frostyBytesTheme.colors.white["400"],
    },
    button: {
    }
})
