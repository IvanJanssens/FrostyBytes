import {HomeScreen} from "../../screens/home/HomeScreen";
import {ItemsScreen} from "../../screens/items/ItemsScreen";
import {CategoriesScreen} from "../../screens/categories/CategoriesScreen";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {faAppleWhole, faAsterisk, faHouse, faPlus, faTags} from "@fortawesome/free-solid-svg-icons";
import TabBarIcon from "./TabBarIcon";
import {screenOptions} from "./tabs.styles";
import TabBarButton from "./TabBarButton";
import {EmptyScreen} from "../../screens/empty/EmptyScreen";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import FridgesScreen from "../../screens/fridges/FridgesScreen";
import React, {Fragment, useState} from "react";
import {useNavigationState} from "@react-navigation/native";
import {ObjectModal} from "../modal/ObjectModal";
import FridgeForm from "../../screens/fridges/FridgeForm";


const Tab = createBottomTabNavigator();

export const Tabs = () => {
    const navigationState = useNavigationState(state => state);
    const [modalVisible, setModalVisible] = useState(false);

    const openModal = () => {
        setModalVisible(true)
    }

    const renderCreateComponent = () => {
        const currentTab = navigationState?.routes[navigationState.index].name;
        switch (currentTab) {
            case 'Home':
                return null;
            case 'Fridges':
                return <FridgeForm setModalVisible={setModalVisible}></FridgeForm>;
            case 'Items':
                return null;
            case 'Categories':
                return null;
            default:
                return null;
        }
    };
    return (
        <Fragment >
            <ObjectModal isVisible={modalVisible} setModalVisible={setModalVisible} reactElement={renderCreateComponent()}></ObjectModal>
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
               <Tab.Screen name="Item" component={EmptyScreen}
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

                                   <TabBarButton {...props} onPress={openModal} ></TabBarButton>
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
        </Fragment>
   )
 }


