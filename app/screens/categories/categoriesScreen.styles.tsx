import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {StyleSheet} from 'react-native';

export const styles = StyleSheet.create({
    container:{
        flex:1,
        alignItems:'center',
        justifyContent:'center',
        backgroundColor: frostyBytesTheme.colors.gray["50"]
    },
    listContainer: {
        flex:1,
        width:"100%",

    },
    listItem: {
        marginTop: 10,
        marginBottom:10
    }
})
