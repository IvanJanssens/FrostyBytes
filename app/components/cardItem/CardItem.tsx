import {Text, TouchableHighlight, TouchableOpacity, View} from "react-native";
import {frostyBytesTheme} from "../../constants/frostyBytesTheme";
import {FontAwesomeIcon} from "@fortawesome/react-native-fontawesome";
import {styles} from "./CardItem.styles";
import {IconProp} from "@fortawesome/fontawesome-svg-core";
import {faTrashCan} from "@fortawesome/free-solid-svg-icons";


const CardItem = ({icon,title,subTitle,extraInfo,onDeletePress, onCardPress}:
                      {icon: IconProp, title: string, subTitle?: string, extraInfo?: string,onDeletePress:()=>void,onCardPress:()=>void }) => {
    return (
        <TouchableHighlight
            style={styles.container}
            underlayColor={frostyBytesTheme.colors.white["400"]}
            onPress={onCardPress}>
                <View style={styles.card}>
                    <View style={styles.itemSection}>
                        <View style={styles.iconContainer}>
                            <FontAwesomeIcon
                                             icon={icon}
                                             size={frostyBytesTheme.sizes.xxLarge}
                                             color="#A2D2FF"
                            />
                        </View>
                        <View style={styles.textContainer}>
                            <Text style={styles.title}>{title}</Text>
                            <View>
                                <Text style={styles.subTitle}>
                                    {subTitle}
                                </Text>
                                <Text style={styles.subTitle}>
                                    {extraInfo}
                                </Text>
                            </View>

                        </View>
                    </View>
                    <View>
                        <TouchableOpacity onPress={onDeletePress}>
                            <FontAwesomeIcon style={styles.actionIcon}
                                             icon={faTrashCan}
                                             size={frostyBytesTheme.sizes.small}
                                             color={frostyBytesTheme.colors.gray["700"]
                                             }
                            />
                        </TouchableOpacity>
                    </View>
                </View>
        </TouchableHighlight>
    );
};
export default CardItem;
