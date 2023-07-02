import React from 'react';
import {StyleSheet} from 'react-native';
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";

export const styles = StyleSheet.create({
    container: {
        top: -40,
        justifyContent:"center",
        alignItems:"center",

    },
    button:{
        width: 50,
        height: 50,
        borderRadius: 35,
        backgroundColor: frostyBytesTheme.colors.primary["600"],
        ...frostyBytesTheme.shadows.small
    }
})
