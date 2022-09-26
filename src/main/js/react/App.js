import Header from "./Header";
import Footer from "./Footer";
import MainSection from "./Main_section";
import useCheckLogin from "./CheckLogin";

function App() {

const [data] = useCheckLogin()

   return (
   <div>
      <Header isLogged={data} />
      <MainSection />
      <Footer />
    </div>
   )
}

export default App
