import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {BottomTabNavigationOptions} from "@react-navigation/bottom-tabs";


export const screenOptions: BottomTabNavigationOptions = {
    tabBarStyle: {
        backgroundColor: frostyBytesTheme.colors.white["400"],
        borderRadius: 15,
        height: 90,
        ...frostyBytesTheme.shadows.medium,
        margin: 5,
    },
    tabBarShowLabel: false
}


