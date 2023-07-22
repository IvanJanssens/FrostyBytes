import {StyleSheet} from "react-native";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";

export const viewStyles = StyleSheet.create({
    centeredView: {
        flex:1,
        justifyContent: "flex-start",
        alignItems: "center",
        top:"12%",


    },
    modalView: {
        backgroundColor: frostyBytesTheme.colors.white["400"],
        height:"65%",
        width:"80%",
        borderRadius: 35,
        ...frostyBytesTheme.shadows.small,
        flexDirection:"row",
        justifyContent:"space-between",
        padding:25

    }, reactNodeContainer: {
        marginTop:50,
    }


});