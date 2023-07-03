import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {BottomTabNavigationOptions} from "@react-navigation/bottom-tabs";


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
