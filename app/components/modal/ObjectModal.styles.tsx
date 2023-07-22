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
        ...frostyBytesTheme.shadows.small

    }, reactNodeContainer: {
        alignSelf:"center",
        marginTop: 50,
        height: 'calc(100% - 50px)',
        paddingBottom: 50,
        borderRadius: 35,
        width:"90%",
    },


    closeIconContainer:{
        position: "absolute",
        top: 0,
        right: 0,
        padding: 20
    }
});
