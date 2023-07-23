import {Button, Text, TextInput} from "react-native-paper";
import React, {useState} from "react";
import { useMutation} from "@apollo/client";
import {withApollo} from "@apollo/client/react/hoc";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {styles} from "./fridgeForm.styles";
import {ActivityIndicator, View} from "react-native";
import { graphql } from '../../src/gql'
import {Fridge} from "../../src/gql/graphql";



const CREATE_FRIDGE = graphql(`
    mutation CreateFridge($name: String!) {
        createFridge(name: $name)
    }
`);

const UPDATE_FRIDGE = graphql(`
    mutation updateFridge($id: ID!, $name: String!) {
        updateFridge(id: $id, name: $name)
    }
`);

const FridgeForm = ({fridge, toggleModal}: {
    fridge: Fridge | null,
    toggleModal: () => void
}) => {
    const [name, setName] = useState(fridge?.name || '');
    const [updateMutation, updateMutationResult] = useMutation(
         UPDATE_FRIDGE
    );
    const [createMutation, createMutationResult] = useMutation(
        CREATE_FRIDGE
    );

    const onSubmit = async () => {
        try {
          fridge ? await updateMutation({
                variables:{id: fridge.id, name}
            }) : await createMutation({variables:{name}});
            toggleModal();
        } catch (error) {
            console.error('Mutation error', error);
        }
    };

    if (updateMutationResult.loading || createMutationResult.loading) return <ActivityIndicator color={frostyBytesTheme.colors.secondary['500']}/>;
    if (updateMutationResult.error || createMutationResult.error) return <View>`Submission error!`</View>;

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
    fridge: Fridge | null,
    toggleModal: () => void
}>(FridgeForm);
