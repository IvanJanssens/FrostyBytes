import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {BottomTabNavigationOptions} from "@react-navigation/bottom-tabs";


export const screenOptions: BottomTabNavigationOptions = {
    tabBarStyle: {
        backgroundColor: frostyBytesTheme.colors.white["400"],
        borderTopLeftRadius: 15,
        borderTopRightRadius: 15,
        height: 90,
        ...frostyBytesTheme.shadows.medium,
    },
    tabBarShowLabel: false
}


