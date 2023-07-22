import {TouchableOpacity, View} from "react-native";
import React, {ReactNode} from "react";
import {styles} from "./tabBarButton.styles";

const TabBarButton = ({children, onPress}: {children: ReactNode, onPress: ()=> void}) => {
    return (
        <TouchableOpacity style={styles.container}
                          onPress={onPress}
        >
            <View style={styles.button}>
                {children}
            </View>
        </TouchableOpacity>
    );
};

export default TabBarButton;
