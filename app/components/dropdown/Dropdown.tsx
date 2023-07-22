import {SelectList} from "react-native-dropdown-select-list/index";
import React from "react";


export const DropDown = () => {
    const [selected, setSelected] = React.useState("");
    const data2 = [
        {key: '1', value: 'Mobiles', disabled: true},
        {key: '2', value: 'Appliances'},
        {key: '3', value: 'Cameras'},
        {key: '4', value: 'Computers', disabled: true},
        {key: '5', value: 'Vegetables'},
        {key: '6', value: 'Diary Products'},
        {key: '7', value: 'Drinks'},
    ]

    return (
        <SelectList
            setSelected={(val: React.SetStateAction<string>) => setSelected(val)}
            data={data2}
            save="value"
        />)
}