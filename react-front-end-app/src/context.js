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
         this.getAllProducts();
    }

    getAllProducts=()=>{
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

    incrementAndDecrement=()=>{

        fetch('http://localhost:9090/price/total',{
            method:'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type':'application/json'
            },
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
               products:result.products
           },()=>{
            this.state.totalPrice.products.forEach(p=>{
                console.log(`price By Single Unit ${p.priceByUnit}`);
            })
           })
        },error=>{
            this.setState({
                isLoaded:true,
                error
            })
        });

    }

    deleteProduct=(product)=>{
        fetch('http://localhost:9090/products/delete',{
            method:'DELETE',
            headers:{
                'content-type':'application/json'
            },
            body:JSON.stringify({
                product:product
            })
        })
            .then(()=>{
                this.setState({
                     isLoaded:true
                })
            },error=>{
                this.setState({
                    isLoaded:true,
                    error
                })
            });
        this.getAllProducts();
    }

    getProduct=(id)=>{
         const product=this.state.products.find(prod=>prod.productId===id);

         console.log(product);
    }

    increment=(id)=>{

        let tempProducts=[...this.state.products];

        const selectedProduct=tempProducts.find(prod=>prod.productId===id);

        const index=tempProducts.indexOf(selectedProduct);

        const product=tempProducts[index];

        product.quantity=product.quantity+1;

         this.incrementAndDecrement();
    }

    decrement=(id)=>{
        
        let tempProducts=[...this.state.products];

        const selectedProduct=tempProducts.find(prod=>prod.productId===id);

        const index=tempProducts.indexOf(selectedProduct);

        const product=tempProducts[index];

        product.quantity=product.quantity-1;

        this.incrementAndDecrement();
    }

    removeProduct=(id)=>{

       const deleteProduct= this.getProduct(id);

       this.deleteProduct(deleteProduct);

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