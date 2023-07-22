import React from 'react';
import {Tabs} from "../components/navigation/Tabs";
import {NavigationContainer} from "@react-navigation/native";
import {frostyBytesTheme} from "../constants/frostyBytesTheme";


const App = () => {
    return (
    <NavigationContainer>
        <Tabs />
    </NavigationContainer>
    )
}

export default App;


