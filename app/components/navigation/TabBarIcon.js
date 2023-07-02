import React from 'react';
import { View, Text } from 'react-native';
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {FontAwesomeIcon} from "@fortawesome/react-native-fontawesome";


const getColor = (focused,activeColor,inActiveColor) => {
    return focused ? activeColor: inActiveColor;
}

const TabBarIcon = (focused,
                    label,
                    icon,
                    size= frostyBytesTheme.sizes.xLarge,
                    activeColor = frostyBytesTheme.colors.primary['600'],
                    inActiveColor = frostyBytesTheme.colors.secondary['500']) => {
    return (
        <View style={{ alignItems: 'center', justifyContent: 'center'}}>
            <FontAwesomeIcon
                icon={icon}
                size={size}
                color={getColor(focused,activeColor,inActiveColor)}
            />
            <Text
                style={{
                    color: getColor(focused,activeColor,inActiveColor),
                    fontSize: frostyBytesTheme.sizes.small,
                }}
            >
                {label}
            </Text>
        </View>
    );
};

export default TabBarIcon;
