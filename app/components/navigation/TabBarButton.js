import {TouchableOpacity, View} from "react-native";
import React from "react";
import {styles} from "./tabBarButton.styles";


const TabBarButton = ({children, onPress}) => {
    return (
        <TouchableOpacity style={styles.container}>
            <View style={styles.button}>
                {children}
            </View>
        </TouchableOpacity>
    );
};

export default TabBarButton;
