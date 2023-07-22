import {HomeScreen} from "../../screens/home/HomeScreen";
import {ItemsScreen} from "../../screens/items/ItemsScreen";
import {CategoriesScreen} from "../../screens/categories/CategoriesScreen";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {faAppleWhole, faAsterisk, faHouse, faPlus, faTags} from "@fortawesome/free-solid-svg-icons";
import TabBarIcon from "./TabBarIcon";
import {screenOptions} from "./tabs.styles";
import TabBarButton from "./TabBarButton";
import {ItemScreen} from "../../screens/item/ItemScreen";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import FridgesScreen from "../../screens/fridges/FridgesScreen";
import React from "react";


const Tab = createBottomTabNavigator();
const openModelBasedOnNavigationState = () => {
    console.log("clicked")

}
export const Tabs = () => {

    return (
       <Tab.Navigator screenOptions={screenOptions}>
           <Tab.Screen name="Home" component={HomeScreen}
           options={{
               tabBarIcon:({focused}) => (
                   TabBarIcon(focused,"Home",faHouse)
               )
           }}
           ></Tab.Screen>
           <Tab.Screen
               name="Fridges"
               component={FridgesScreen}
               options={{
                    tabBarIcon: ({focused}) => (
                        TabBarIcon(focused, "Fridges", faAsterisk)
                    )
                }}
                />
           <Tab.Screen name="Item" component={ItemScreen}
                       options={{
                           tabBarIcon:({focused}) => (
                               TabBarIcon(focused,
                                   "",
                                   faPlus,
                                   frostyBytesTheme.sizes.xLarge,
                                   frostyBytesTheme.colors.white["400"],
                                   frostyBytesTheme.colors.white["400"]
                                   )
                           ),
                           tabBarButton: (props) =>(

                               <TabBarButton {...props} onPress={openModelBasedOnNavigationState} ></TabBarButton>
                           )
                       }}
           />
           <Tab.Screen name="Items" component={ItemsScreen}
                       options={{
                           tabBarIcon:({focused}) => (
                               TabBarIcon(focused,"Items",faAppleWhole)
                           )
                       }}
           />
           <Tab.Screen name="Categories" component={CategoriesScreen}
                       options={{
                           tabBarIcon:({focused}) => (
                               TabBarIcon(focused,"Categories",faTags)
                           )
                       }}
           />
       </Tab.Navigator>
   )
 }


