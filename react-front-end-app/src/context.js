import React, { Component } from 'react';
import {productTotal} from './data';

const ProductContext=React.createContext();

class ProductProvider extends Component {

    constructor(props)
    {
        super(props);
        this.state={
            products:[],
            productSubTotal: 0,
            productTotal:0,
            isLoaded:false,
            totalPrice:{}
        }
    }

    componentDidMount()
    {
        fetch('http://localhost:9090/products')
         .then(res=>res.json())
         .then(result=>{
            this.setState({
                isLoaded:true,
                products:result
            },()=>{
                console.log(result);
            });
         },error=>{
             this.setState({
                 isLoaded:true,
                 error
             })
         }
         );
    }

    sendRequest=()=>{

        fetch('http://localhost:9090/price/total',{
            method:'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify({

                    products:this.state.products
                
            })
        }).then(res=>{
            console.log(res);
            return res.json();
        })
        .then(result=>{
           this.setState({
               isLoaded:true,
               totalPrice:result,
           },()=>{
            console.log(this.state.totalPrice);
           })
        },error=>{
            this.setState({
                isLoaded:true,
                error
            })
        });

    }

    increment=(id)=>{

        let tempProducts=[...this.state.products];

        const selectedProduct=tempProducts.find(prod=>prod.productId===id);

        const index=tempProducts.indexOf(selectedProduct);

        const product=tempProducts[index];

        product.quantity=product.quantity+1;

        this.sendRequest();
        //todo
        // this.setState({
            
        // },()=>{
        //     console.log(`count is ${this.state.productTotal.quantity}`);
        // })
    }

    decrement=(id)=>{
        //todo
        console.log('decrement Method');
    }

    removeProduct=(id)=>{
        //todo
       let tempProducts=[...this.state.products];

    }

    addTotals=()=>{
        let total=0.0;
        this.state.products.map((prod)=>{
            total+=prod.priceOfCartoon;
        })
    }

    render() {
        return (
            <ProductContext.Provider value={{...this.state,
            increment:this.increment,
            decrement:this.decrement,
            removeProduct:this.removeProduct}}>
                {this.props.children}
                </ProductContext.Provider>
        );
    }
}

const ProductConsumer=ProductContext.Consumer;

export {ProductContext,ProductProvider,ProductConsumer};