const path = require("path")
const common = require("./webpack.common")
const { merge } = require("webpack-merge")
var HtmlWebpackPlugin = require("html-webpack-plugin")

module.exports = merge(common, {
    mode: "development",
    output: {
        filename: "[name].bundle.js",
        path: path.resolve(__dirname, "./src/main/resources/static/built"),
        publicPath: "/"
    },
    devServer: {
        historyApiFallback: true,
      },
    plugins:
    [new HtmlWebpackPlugin({
        template: "./src/main/resources/templates/index.html"
    })],
    module: {
        rules: [{
            test: /\.scss$/,
            use: ["style-loader", "css-loader", "sass-loader"]
        }]
    }
})
