const path = require("path")

module.exports = {
    entry: {
        main: "./src/index.js",
        vendor: "./src/vendor.js"
    },
    module: {
        rules: [
        {
            test: /\.html$/,
            use: ["html-loader"]
        },
        {
            test: /\.jsx?$/,
            exclude: /node-modules/,
            use: {
                loader: "babel-loader",
                options: {
                    cacheDirectory: true,
                    cacheCompression: false
                }
            }
        },
    {
        test: /\.(svg|png|jpg|gif)$/,
        use: {
            loader: "file-loader",
            options: {
                name: "[name].[hash].[ext]",
                outputPath: "imgs"
            }
        }
    }]
    }
}   