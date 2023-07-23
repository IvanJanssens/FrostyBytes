import {Modal, Pressable, View} from "react-native";
import {ReactNode} from "react";
import {viewStyles} from "./ObjectModal.styles";
import {FontAwesomeIcon} from "@fortawesome/react-native-fontawesome";
import {styles} from "../cardItem/CardItem.styles";
import {faClose} from "@fortawesome/free-solid-svg-icons";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";


export const ObjectModal = ({isVisible, toggleModal, reactElement}:
                                {isVisible: boolean, toggleModal: () => void, reactElement: ReactNode | null}) =>{
    return (
        <Modal
            animationType="fade"
            transparent={true}
            visible={isVisible}
            onRequestClose={toggleModal}
        >
        <View style={viewStyles.centeredView} >
            <View style={viewStyles.modalView}>
                <Pressable style={viewStyles.closeIconContainer}
                    onPress={toggleModal}>
                    <FontAwesomeIcon style={styles.actionIcon}
                                     icon={faClose}
                                     size={frostyBytesTheme.sizes.large}
                                     color={frostyBytesTheme.colors.gray["700"]}
                    />
                </Pressable>
                <View style={viewStyles.reactNodeContainer}>{reactElement}</View>
            </View>
        </View>
    </Modal>)
}
