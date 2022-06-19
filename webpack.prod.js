const path = require("path")
const common = require("./webpack.common")
const { merge } = require("webpack-merge")
const { CleanWebpackPlugin } = require("clean-webpack-plugin")
const MiniCssExtractPlugin = require("mini-css-extract-plugin")
const CssMinimizerWebpackPlugin = require("css-minimizer-webpack-plugin")
const TerserPlugin = require("terser-webpack-plugin")
var HtmlWebpackPlugin = require("html-webpack-plugin")

module.exports = merge(common, {
    mode: "production",
    output: {
        filename: "[name].[contentHash].bundle.js",
        path: path.resolve(__dirname, "./src/main/resources/static/built"),
        publicPath: "/"
    },
    optimization: {
        minimizer: [
            new CssMinimizerWebpackPlugin(),
            new TerserPlugin()
        ]
    },
    devServer: {
        historyApiFallback: true,
      },
    plugins: [
        new MiniCssExtractPlugin({filename: "[name].[contentHash].css"}),
        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            template: "./src/main/resources/templates/index.html",
            minify: {
                removeAttributeQuotes: true,
                collapseWhiteSpace: true,
                removeComments: true
            }
    })
    ],
    module: {
        rules: [{
            test: /\.scss$/,
            use: [MiniCssExtractPlugin.loader, "css-loader", "sass-loader"]
        }]
    }
})
