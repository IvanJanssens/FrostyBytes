import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {BottomTabNavigationOptions} from "@react-navigation/bottom-tabs";
import {StyleSheet} from "react-native";
import create = StyleSheet.create;


export const screenOptions: BottomTabNavigationOptions = {
    tabBarStyle: {
        position: 'absolute',
        bottom: 25,
        left: 20,
        right: 20,
        backgroundColor: frostyBytesTheme.colors.white["400"],
        borderRadius: 15,
        height: 90,
        ...frostyBytesTheme.shadows.medium,
    },
    tabBarShowLabel: false
}

export const viewStyles = create({
    centeredView: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: 22,
    },
    modalView: {
        height: '80vh',
        width: '80vw',
        margin: 20,
        backgroundColor: 'white',
        borderRadius: 20,
        padding: 35,
        alignItems: 'center',
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5,
    },
    button: {
        borderRadius: 20,
        padding: 10,
        elevation: 2,
    },
    buttonOpen: {
        backgroundColor: '#F194FF',
    },
    buttonClose: {
        backgroundColor: '#2196F3',
    },
    textStyle: {
        color: 'white',
        fontWeight: 'bold',
        textAlign: 'center',
    },
    modalText: {
        marginBottom: 15,
        textAlign: 'center',
    },
});
