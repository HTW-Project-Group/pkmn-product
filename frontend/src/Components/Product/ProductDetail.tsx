import * as React from "react";
import Box from "@mui/material/Box";
import { createTheme, responsiveFontSizes } from "@mui/material/styles";
import { useEffect, useState } from "react";
import Card from "../../Model/Card";
import Pokemon from "../../Model/Pokemon";
import AddToBasket from "../Basket/AddToBasket";

let theme = createTheme();
theme = responsiveFontSizes(theme);

export default function ProductDetails({
  id,
  name,
  price,
  description,
  condition,
  pokemonId,
}: Card) {
  const initialEmptyPokemon = {} as Pokemon;
  const [pokemon, setPokemon] = useState<Pokemon>(initialEmptyPokemon);

  useEffect(() => {
    const pokemonDetails = async () => {
      const data = await fetch(
        "http://localhost:8080/v1/pokemon/" + pokemonId,
        {
          method: "GET",
        }
      );
      const jsonData: Pokemon = await data.json();
      const pokemon: Pokemon = {
        id: jsonData.id,
        name: jsonData.name,
        supertype: jsonData.supertype,
        subtypes: jsonData.subtypes,
        hp: jsonData.hp,
        level: jsonData.level,
        types: jsonData.types,
        attacks: jsonData.attacks,
        weaknesses: jsonData.weaknesses,
        resistances: jsonData.resistances,
        set: jsonData.set,
        number: jsonData.number,
        artist: jsonData.artist,
        rarity: jsonData.rarity,
      };
      return pokemon;
    };
    pokemonDetails().then((result) => setPokemon(result));
  }, [pokemonId]);

  return (
    <Box className="productDetail">
      <Box className="detailImageContainer">
        <img
          alt="pokemon"
          className="detailImage"
          src={
            "https://images.pokemontcg.io/" +
            pokemonId.replaceAll("-", "/") +
            "_hires.png"
          }
        />
      </Box>
      <Box className="details">
        <Box className="descriptionHeader">
          <h1 className="pokemonName">{pokemon.name}</h1>
          {pokemon.types != null &&
            pokemon.types.map((type) => (
              <Box className="descriptionPokemonType" key={type}>
                <Box className={"energy-icon " + type} />
              </Box>
            ))}
        </Box>
        <Box className="information">
          <table>
            <tbody>
              <tr>
                <th scope="row" className="descriptionPartOneTd">
                  <p className="descriptionPart">Card Type: </p>
                </th>
                <td className="descriptionPartTwoTd">
                  <p className="descriptionPart">{pokemon.supertype}</p>
                </td>
              </tr>
              {pokemon.hp != null && (
                <tr>
                  <th scope="row" className="descriptionPartOneTd">
                    <p className="descriptionPart">Health Points: </p>
                  </th>
                  <td className="descriptionPartTwoTd">
                    <p className="descriptionPart">{pokemon.hp}HP</p>
                  </td>
                </tr>
              )}
              <tr>
                <th scope="row" className="descriptionPartOneTd">
                  <p className="descriptionPart">Rarity: </p>
                </th>
                <td className="descriptionPartTwoTd">
                  <p className="descriptionPart">{pokemon.rarity}</p>
                </td>
              </tr>
              {pokemon.attacks != null && (
                <tr>
                  <th scope="row" className="descriptionPartOneTd">
                    <p className="descriptionPart"> Attack Name:</p>
                  </th>

                  <td className="descriptionPartTwoTd">
                    {pokemon.attacks.map((attack) => (
                      <p className="descriptionPart" key={attack.name}>
                        {attack.name}
                      </p>
                    ))}
                  </td>
                </tr>
              )}

              <tr>
                <th scope="row" className="descriptionPartOneTd">
                  <p>Set Name: </p>
                </th>
                <td>
                  {pokemon.set != null && (
                    <p className="descriptionPartTwoTd">{pokemon.set.name}</p>
                  )}
                </td>
              </tr>
            </tbody>
          </table>

          <table>
            <thead>
              <tr>
                {pokemon.weaknesses != null && (
                  <th scope="col" className="weaknesses">
                    Weaknesses:
                  </th>
                )}
                {pokemon.resistances != null && (
                  <th scope="col">Resistances:</th>
                )}
              </tr>
            </thead>
            <tbody>
              <tr>
                {pokemon.weaknesses != null &&
                  pokemon.weaknesses.map((weaknesses) => (
                    <td className="descriptionPartTwoTd" key={weaknesses.type}>
                      <Box className="energyMultiplicator">
                        <Box
                          className={"energy-icon" + " " + weaknesses.type}
                        ></Box>
                        <p>{weaknesses.value}</p>
                      </Box>
                    </td>
                  ))}
                {pokemon.resistances != null && pokemon.resistances.length == 1
                  ? pokemon.resistances.map((resistances) => (
                      <td
                        className="descriptionPartTwoTd"
                        key={resistances.type}
                      >
                        <Box className="energyMultiplicator">
                          <Box
                            className={"energy-icon" + " " + resistances.type}
                          ></Box>
                          <p>{resistances.value}</p>
                        </Box>
                      </td>
                    ))
                  : pokemon.resistances != null &&
                    pokemon.resistances.length == 2 && (
                      <td className="descriptionPartTwoTd">
                        <Box className="energyMultiplicator">
                          <Box
                            className={
                              "energy-icon" + " " + pokemon.resistances[0].type
                            }
                          ></Box>
                          <Box
                            className={
                              "energy-icon" + " " + pokemon.resistances[1].type
                            }
                          ></Box>
                          <p>{pokemon.resistances[0].value}</p>
                        </Box>
                      </td>
                    )}
              </tr>
            </tbody>
          </table>
        </Box>
        <Box className={"addToBasketContainer"}>
          <AddToBasket price={price} />
        </Box>
        <Box className="descriptionFooter">
          <br />
          <p className="artistName">Illustrator: {pokemon.artist}</p>
        </Box>
      </Box>
    </Box>
  );
}
