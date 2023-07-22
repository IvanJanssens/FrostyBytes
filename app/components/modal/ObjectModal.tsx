import {Alert, Modal, Pressable, Text, View} from "react-native";
import {Dispatch, ReactNode, SetStateAction, useState} from "react";
import {viewStyles} from "./ObjectModal.styles";
import {FontAwesomeIcon} from "@fortawesome/react-native-fontawesome";
import {styles} from "../cardItem/CardItem.styles";
import {faClose, faCross, faEraser, faTrashCan} from "@fortawesome/free-solid-svg-icons";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";


export const ObjectModal = ({isVisible, setModalVisible, reactNode}:
                                {isVisible: boolean,setModalVisible:Dispatch<SetStateAction<boolean>>, reactNode: ReactNode}) =>{
    return (<Modal
        animationType="fade"
        transparent={true}
        visible={isVisible}
        onRequestClose={() => {
            Alert.alert('Modal has been closed.');
            setModalVisible(false)
        }} >
        <View style={viewStyles.centeredView} >
            <View style={viewStyles.modalView}>
                <View style={viewStyles.reactNodeContainer}>{reactNode}</View>
                <Pressable
                    onPress={() => setModalVisible(!isVisible)}>
                    <FontAwesomeIcon style={styles.actionIcon}
                                     icon={faClose}
                                     size={frostyBytesTheme.sizes.large}
                                     color={frostyBytesTheme.colors.gray["700"]}
                    />
                </Pressable>
            </View>
        </View>
    </Modal>)
}
