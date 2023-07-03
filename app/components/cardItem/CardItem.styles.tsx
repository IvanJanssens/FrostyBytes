import React from 'react';
import {Dimensions, StyleSheet} from 'react-native';
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";


export const styles = StyleSheet.create({
    container: {
        width:"100%",
        alignItems:"center",
        backgroundColor: frostyBytesTheme.colors.gray["50"]
    },
    card: {
        flex:1,
        height: "auto",
        width: "90%",
       padding: 15,
        backgroundColor: frostyBytesTheme.colors.white["400"],
        borderRadius: 10,
    },
    title: {
        fontSize: frostyBytesTheme.sizes.medium,
        fontWeight: 'bold'
    },
    subTitle:{
        fontSize: frostyBytesTheme.sizes.small,
        fontWeight: 'normal' ,
        marginTop: 2
    }
})
