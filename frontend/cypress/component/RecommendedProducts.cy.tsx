import * as React from 'react'
import RecommendedProducts from "../../src/Components/RecommendedProducts";
import {BrowserRouter} from "react-router-dom";

describe('RecommendedProducts.cy.ts', () => {
    it('playground', () => {
        cy.mount(
            <BrowserRouter>
                <RecommendedProducts/>
            </BrowserRouter>
        )
    })
})