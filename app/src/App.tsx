import React from 'react';
import {Tabs} from "../components/navigation/Tabs";
import {DefaultTheme, NavigationContainer} from "@react-navigation/native";
import {frostyBytesTheme} from "../constants/frostyBytesTheme";

const MyTheme = {
    ...DefaultTheme,
    colors: {
        ...DefaultTheme.colors,
        background: frostyBytesTheme.colors.gray["50"],
    },
};
const App = () => {
    return (
    <NavigationContainer theme={MyTheme}>
        <Tabs />
    </NavigationContainer>
    )
}

export default App;


