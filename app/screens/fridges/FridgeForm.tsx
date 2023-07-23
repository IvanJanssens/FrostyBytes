import {Button, Text, TextInput} from "react-native-paper";
import React, {useState} from "react";
import {gql, useMutation} from "@apollo/client";
import {withApollo} from "@apollo/client/react/hoc";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {styles} from "./fridgeForm.styles";
import {ActivityIndicator, View} from "react-native";

interface FridgeFormProps {
    id: string;
    name: string;
}

const ADD_FRIDGE = gql`
    mutation CreateFridge($name: String!) {
        createFridge(name: $name)
    }
`;

const UPDATE_FRIDGE = gql`
    mutation updateFridge($id: ID!, $name: String!) {
        updateFridge(id: $id, name: $name)
    }
`;

const FridgeForm = ({fridge, toggleModal}: {
    fridge: FridgeFormProps | null,
    toggleModal: () => void
}) => {
    const [name, setName] = useState(fridge?.name || '');
    const [mutateFunction, {loading, error}] = useMutation(
        fridge ? UPDATE_FRIDGE : ADD_FRIDGE
    );

    const onSubmit = async () => {
        try {
            const {data} = await mutateFunction({
                variables: {id: fridge?.id, name},
            });
            toggleModal()

            if (fridge) {

                // onUpdateComplete(data.updateFridge);
            } else {

                // onCreateComplete(data.createFridge);
            }
        } catch (error) {
            console.error('Mutation error', error);
        }
    };

    if (loading) return <ActivityIndicator color={frostyBytesTheme.colors.secondary['500']}/>;
    if (error) return <View>`Submission error! ${error.message}`</View>;

    return (
        <View style={styles.container}>
            <Text variant="displaySmall" style={styles.text}>
                {fridge ? 'Update Fridge' : 'New Fridge'}
            </Text>
            <TextInput
                label="Name"
                mode="outlined"
                value={name}
                outlineColor={frostyBytesTheme.colors.tertiary['400']}
                activeOutlineColor={frostyBytesTheme.colors.tertiary['400']}
                onChangeText={setName}
                outlineStyle={styles.inputField}
                autoFocus={true}
            />
            <Button
                icon="fridge"
                mode="elevated"
                buttonColor={frostyBytesTheme.colors.white['400']}
                textColor={frostyBytesTheme.colors.tertiary['600']}
                onPress={onSubmit}
                style={styles.button}
            >
                {fridge ? 'Update fridge' : 'Add fridge'}
            </Button>
        </View>
    );
};

export default withApollo<{
    fridge: FridgeFormProps | null,
    toggleModal: () => void
}>(FridgeForm);
