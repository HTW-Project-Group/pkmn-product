export default interface Pokemon {
  id: string;
  name: string;
  supertype: string;
  subtypes: string[];
  hp: number;
  level: number;
  types: string[];
  attacks: Attacks[];
  weaknesses: TypeValue[];
  resistances: TypeValue[];
  set: Set;
  number: number;
  artist: string;
  rarity: string;
}

interface Attacks {
  name: string;
  cost: string[];
  convertedEnergyCost: number;
  damage: string;
  text: string;
}

interface TypeValue {
  type: string;
  value: string;
}

interface Set {
  id: string;
  name: string;
  series: string;
  printedTotal: number;
  total: number;
  ptcgoCode: string;
  releaseDate: string;
}
