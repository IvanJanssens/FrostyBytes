import {Text, TouchableHighlight, View} from "react-native";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {FontAwesomeIcon} from "@fortawesome/react-native-fontawesome";
import {styles} from "./CardItem.styles";
import {IconProp} from "@fortawesome/fontawesome-svg-core";


const CardItem = ({icon}: {icon: IconProp}) => {
    return (
        <TouchableHighlight
            style={styles.container}
            underlayColor={frostyBytesTheme.colors.white["400"]}
            onPress={() => console.log("navigate")}>
            <View style={styles.card}>
                <View>
                    <FontAwesomeIcon
                        icon={icon}
                        size={frostyBytesTheme.sizes.xLarge}
                        color={frostyBytesTheme.colors.primary["600"]}
                    />
                </View>
                <View>
                    <Text style={styles.title}>name</Text>
                    <Text style={styles.subTitle}>
                        category
                    </Text>
                </View>
            </View>
        </TouchableHighlight>
    );
};
export default CardItem;
