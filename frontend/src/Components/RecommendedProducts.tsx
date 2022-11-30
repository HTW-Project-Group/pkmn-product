import * as React from "react";
import Cards from "./Cards"
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import {SetStateAction, useEffect, useState} from "react";


// auslagern
interface Card {
    id: string;
    name: string;
    price: number;
}

export default function RecommendedProducts() {


    const [products, setProducts] = useState([]);

    useEffect(() => {
        const productList = async () => {
            const data = await fetch("http://localhost:8080/products", {
                method: "GET"
            });
            const jsonData = await data.json();
            const result = jsonData.map((data: any) => {
                const card: Card = {
                    id: data.id,            // default werte beachten
                    name: data.name,
                    price: data.price
                }
                return card;
            });
            return result;
        };

        productList().then(result => {
            console.log(result);
            setProducts(result);
        });
    }, []);




    return (

        <Box>

            {products.map( (item: Card) => (
                <Cards key={item.id} name={item.name} id={item.id} price={item.price}
                       image="https://assets.pokemon.com/assets/cms2-de-de/img/cards/web/XY3/XY3_DE_81.png"/>

            ))}
        </Box>
    );
}