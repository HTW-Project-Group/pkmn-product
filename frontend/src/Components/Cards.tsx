import * as React from "react";
import Box from "@mui/material/Box";
import Card from '@mui/material/Card';
import ButtonBase from '@mui/material/ButtonBase';
import {CardActionArea, CardContent, CardMedia} from '@mui/material';
import Typography from "@mui/material/Typography";


type cardprops = {
    name: string,
    image: string,
    id: string,
    price: number
}


export default function Cards({name, image, id , price}: cardprops) {

    return (
        <Card className={"cardData"} sx={{maxWidth: 300}}>
            <CardActionArea>
                <CardMedia
                    component="img"
                    height="500"
                    image={image}/>
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                        {name}/ {price}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                        Hier steht die Beschreibung
                    </Typography>


                </CardContent>
            </CardActionArea>
        </Card>
    );

}