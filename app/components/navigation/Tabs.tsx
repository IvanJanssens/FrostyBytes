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
import {HomeScreen} from "../../screens/home/HomeScreen";


const Tab = createBottomTabNavigator();

export const Tabs = () => {
    const navigationState = useNavigationState(state => state);
    const [modalVisible, setModalVisible] = useState(false);
    const [formItem, setFormItem] = useState(null);

    const toggleModal = () => {
        if (modalVisible) setFormItem(null)
        setModalVisible(!modalVisible);
    }

    const updateFormItem = (item: any) => {
        setFormItem(item)
    }

    const renderFormComponent = () => {
        const currentTab = navigationState?.routes[navigationState.index].name;
        switch (currentTab) {
            case 'Home':
                return null;
            case 'Fridges':
                return <FridgeForm fridge={formItem} toggleModal={toggleModal}></FridgeForm>;
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
            <ObjectModal isVisible={modalVisible} toggleModal={toggleModal} reactElement={renderFormComponent()}></ObjectModal>
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
                   children={()=><FridgesScreen toggleModal={toggleModal} setItem={updateFormItem}></FridgesScreen>}
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

                                   <TabBarButton {...props} onPress={toggleModal} ></TabBarButton>
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


