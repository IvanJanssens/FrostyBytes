import {Button, Text, TextInput} from "react-native-paper";
import {useState} from "react";
import {gql, useMutation} from "@apollo/client";
import {withApollo} from "@apollo/client/react/hoc";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {styles} from "./fridgeForm.styles";
import {View} from "react-native";


const ADD_FRIDGE = gql`
    mutation CreateFridge($name: String!) {
        createFridge(name: $name)
    }
`;

const FridgeForm = () => {
    const [name, setName] = useState("");
    const [mutateFunction, { data, loading, error }] = useMutation(ADD_FRIDGE);

    if (loading) return <View>'Submitting...'</View>;
    if (error) return <View>`Submission error! ${error.message}`</View>;
    return (
        <View style={styles.container}>
            <Text variant="displaySmall" style={styles.text}>New Fridge</Text>
            <TextInput
                label="Name"
                mode="outlined"
                value={name}
                outlineColor={frostyBytesTheme.colors.tertiary["400"]}
                activeOutlineColor={frostyBytesTheme.colors.tertiary["400"]}
                onChangeText={name => setName(name)}
                outlineStyle={styles.inputField}
            />
            <Button
                icon="fridge"
                mode="elevated"
                buttonColor={frostyBytesTheme.colors.white["400"]}
                textColor={frostyBytesTheme.colors.tertiary["600"]}
                onPress={() => mutateFunction({ variables: { name: name }} )}
                style={styles.button}
            >
                Add fridge
            </Button>
        </View>
    )
}

export default withApollo(FridgeForm);
