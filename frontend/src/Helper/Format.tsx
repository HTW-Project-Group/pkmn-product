export const formatPrice = (price: number): string => {
  return (Math.round(price * 100) / 100).toFixed(2) + " €";
};

export const formatWithMaxLen = (text: string, len: number): string => {
  const EOL = "{{EOL}}";
  let reduced = text
    .split(" ")
    .reduce((a, b) => {
      const t = `${a} ${b}`;
      if (t.includes(EOL)) {
        return a;
      }
      if (t.length - 3 <= len) {
        return t;
      } else {
        return a + EOL;
      }
    })
    .replace(EOL, "...");
  if (reduced.length === 0) {
    reduced = text.substring(0, len - 3) + "...";
  }
  return reduced;
};
