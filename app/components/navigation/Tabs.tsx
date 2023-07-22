import {HomeScreen} from "../../screens/home/HomeScreen";
import {ItemsScreen} from "../../screens/items/ItemsScreen";
import {CategoriesScreen} from "../../screens/categories/CategoriesScreen";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {faAppleWhole, faAsterisk, faHouse, faPlus, faTags} from "@fortawesome/free-solid-svg-icons";
import TabBarIcon from "./TabBarIcon";
import {screenOptions, viewStyles} from "./tabs.styles";
import TabBarButton from "./TabBarButton";
import {ItemScreen} from "../../screens/item/ItemScreen";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import FridgesScreen from "../../screens/fridges/FridgesScreen";
import React, {useState} from "react";
import {Alert, Modal, Pressable, Text, View} from "react-native";
import {useNavigationState} from "@react-navigation/native";


const Tab = createBottomTabNavigator();

export const Tabs = () => {
    const navigationState = useNavigationState(state => state);
    const [modalVisible, setModalVisible] = useState(false);
    const openModelBasedOnNavigationState = () => {
        console.log("clicked")
        setModalVisible(true)
    }
    return (
        <View style={{
            flexDirection: 'row',
            height: '100%',
            width: '100%',
            padding: 20,
        }}>
            <Modal
                animationType="slide"
                transparent={true}
                visible={modalVisible}
                onRequestClose={() => {
                    Alert.alert('Modal has been closed.');
                    setModalVisible(!modalVisible);
                }}>
                <View style={viewStyles.centeredView}>
                    <View style={viewStyles.modalView}>
                        <Text style={viewStyles.modalText}>Hello World!</Text>
                        <Pressable
                            style={[viewStyles.button, viewStyles.buttonClose]}
                            onPress={() => setModalVisible(!modalVisible)}>
                            <Text style={viewStyles.textStyle}>Hide Modal</Text>
                        </Pressable>
                    </View>
                </View>
            </Modal>
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
        </View>
   )
 }


