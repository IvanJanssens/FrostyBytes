import {SafeAreaView, Text, View} from "react-native";
import {styles} from "../home/homeScreen.styles";
import {Button, TextInput} from "react-native-paper";
import {useState} from "react";
import {gql, useMutation} from "@apollo/client";
import {withApollo} from "@apollo/client/react/hoc";


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
        <SafeAreaView style={styles.container}>
            <Text>New fridge</Text>
            <TextInput
                label="Name"
                mode="outlined"
                value={name}
                onChangeText={name => setName(name)}
            />
            <Button
                icon="fridge"
                mode="elevated"
                onPress={() => mutateFunction({ variables: { name: name }} )}
            >
                Add fridge
            </Button>
        </SafeAreaView>
    )
}

export default withApollo(FridgeForm);
