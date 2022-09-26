import Header from "./Header";
import Footer from "./Footer";
import MainSection from "./Main_section";
import useCheckLogin from "./CheckLogin";

function App() {

const [data] = useCheckLogin()

if(data) {
    console.log("Jestem zalogowany")
} else {
    console.log("nie jestem zalogowany")
}

   return (
   <div>
      <Header />
      <MainSection />
      <Footer />
    </div>
   )
}

export default App
