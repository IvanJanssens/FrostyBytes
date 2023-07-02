import {TouchableOpacity, View} from "react-native";
import React, {ReactNode} from "react";
import {styles} from "./tabBarButton.styles";

const TabBarButton = ({children}: {children: ReactNode}) => {
    return (
        <TouchableOpacity style={styles.container}>
            <View style={styles.button}>
                {children}
            </View>
        </TouchableOpacity>
    );
};

export default TabBarButton;
