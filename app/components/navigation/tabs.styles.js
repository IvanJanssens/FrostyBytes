import {StyleSheet} from "react-native";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";



export const screenOptions = StyleSheet.create({
    bar:{
    tabBarStyle: {
        position: 'absolute',
            bottom: 25,
            left: 20,
            right: 20,
            elevation: 0,
            backgroundColor: frostyBytesTheme.colors.white["50"],
            borderRadius: 15,
            height: 90,
    ...frostyBytesTheme.shadows.medium,
    },
    tabBarShowLabel: false
}
})
