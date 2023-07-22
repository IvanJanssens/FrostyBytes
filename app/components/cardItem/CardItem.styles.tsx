import React from 'react';
import {Dimensions, StyleSheet} from 'react-native';
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";


export const styles = StyleSheet.create({
    container: {
        width: "100%",
        alignItems: "center",
        backgroundColor: frostyBytesTheme.colors.gray["50"]
    },
    card: {
        flex: 1,
        height: "auto",
        width: "90%",
        paddingLeft: 10,
        paddingRight:15,
        paddingTop:10,
        paddingBottom:10,
        backgroundColor: frostyBytesTheme.colors.white["400"],
        borderRadius: 10,
        justifyContent: "flex-start",
        alignItems: "flex-start",
        flexDirection: "row",
        gap:10,
        ...frostyBytesTheme.shadows.small
    },
    itemSection: {
        flex: 1,
        height: "auto",
        paddingLeft: 10,
        paddingRight:20,
        paddingTop:10,
        paddingBottom:10,
        justifyContent: "flex-start",
        alignItems: "center",
        flexDirection: "row",
        gap:10
    },

    iconContainer:{
        backgroundColor: "#f7f1ec",
        padding: 12,
        borderRadius: 4,
    },
    actionIcon:{
        padding: 1
    },
    title: {
        fontSize: frostyBytesTheme.sizes.medium,
        fontWeight: 'bold',
        marginTop:1,
        marginBottom:0
    },
    subTitle: {
        fontSize: frostyBytesTheme.sizes.xSmall,
        fontWeight: 'normal',
        marginTop: 0,
        marginBottom: 2
    }
})
