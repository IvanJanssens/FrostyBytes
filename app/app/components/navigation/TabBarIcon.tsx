import React from 'react';
import {Text, View} from 'react-native';
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {FontAwesomeIcon} from "@fortawesome/react-native-fontawesome";
import {IconProp} from "@fortawesome/fontawesome-svg-core";


const getColor = (focused: boolean, activeColor: string, inActiveColor: string) => {
    return focused ? activeColor: inActiveColor;
}

const TabBarIcon = (focused: boolean,
                    label: string,
                    icon: IconProp,
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
