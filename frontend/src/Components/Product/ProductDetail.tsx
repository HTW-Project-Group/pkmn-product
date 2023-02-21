import * as React from "react";
import Box from "@mui/material/Box";
import { useEffect, useState } from "react";
import Pokemon from "../../Model/Pokemon";
import AddToBasket from "../Basket/AddToBasket";
import Card from "../../Model/Card";

export default function ProductDetails({
  pokemonId,
}: {
  pokemonId: string | undefined;
}) {
  const [pokemon, setPokemon] = useState<Pokemon>({} as Pokemon);
  const [product, setProduct] = useState<Card>({} as Card);

  useEffect(() => {
    const pokemonDetails = async () => {
      const data = await fetch(
        "http://localhost:8080/v1/product/pokemon/" + pokemonId,
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
      window.scrollTo(0, 0);
      return pokemon;
    };
    pokemonDetails().then((result) => setPokemon(result));
  }, [pokemonId]);

  useEffect(() => {
    const getProductByPokemonId = async () => {
      const data = await fetch(
        `http://localhost:8080/v1/product/pkmn/${pokemonId}`,
        {
          method: "GET",
        }
      );
      return data.json();
    };

    getProductByPokemonId().then((result) => setProduct(result));
  }, [pokemonId]);

  return (
    <div className="product-detail-root">
      <div className="product-detail-wrapper">
        <div className="image-container">
          <img
            alt="Pokemon Card"
            className="detail-image"
            src={
              "https://images.pokemontcg.io/" +
              pokemonId?.replaceAll("-", "/") +
              "_hires.png"
            }
          />
          <span className="artist-name">Illustrator: {pokemon.artist}</span>
        </div>
        <div className="product-detail-container">
          <div className="name-wrapper">
            <h1 className="pokemon-name">{pokemon.name}</h1>
            {pokemon.types != null &&
              pokemon.types.map((type) => (
                <Box
                  className={"energy-icon " + type.toLowerCase()}
                  key={type}
                />
              ))}
          </div>

          <div className="detail-split">
            <table className="attributes">
              <tr>
                <td>Card Type</td>
                <td>{pokemon.supertype}</td>
              </tr>
              <tr>
                <td>Rarity</td>
                <td>{pokemon.rarity}</td>
              </tr>
              {pokemon.hp != null && (
                <tr>
                  <td>Health Points</td>
                  <td>{pokemon.hp} HP</td>
                </tr>
              )}
              {pokemon.attacks != null && (
                <tr>
                  <td>Attacks</td>
                  <td className="attack-container">
                    {pokemon.attacks.map((attack) => (
                      <p className="attack" key={attack.name}>
                        {attack.name}
                      </p>
                    ))}
                  </td>
                </tr>
              )}
              <tr>
                <td>Set</td>
                <td>{pokemon.set?.name}</td>
              </tr>
            </table>
            <div className="weak-resi-container">
              {pokemon.weaknesses != null && (
                <span className="wr-label">Weaknesses</span>
              )}
              <div className="type-container">
                {pokemon.weaknesses != null &&
                  pokemon.weaknesses.map((w) => (
                    <div className="type-container" key={w.type}>
                      <Box
                        className={"energy-icon" + " " + w.type.toLowerCase()}
                      ></Box>
                      <p>{w.value}</p>
                    </div>
                  ))}
              </div>

              {pokemon.resistances != null && (
                <span className="wr-label">Resistances</span>
              )}
              <div className="type-container">
                {pokemon.resistances != null &&
                  pokemon.resistances.map((r) => (
                    <div className="type-container" key={r.type}>
                      <Box
                        className={"energy-icon" + " " + r.type.toLowerCase()}
                      ></Box>
                      <p>{r.value}</p>
                    </div>
                  ))}
              </div>
            </div>
          </div>
        </div>
      </div>

      <h2>About this Card</h2>
      <p>{product.description}</p>

      <div className="add-to-basket-container">
        <AddToBasket product={product} />
      </div>
    </div>
  );
}
