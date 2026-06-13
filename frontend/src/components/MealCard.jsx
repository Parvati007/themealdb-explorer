import { useNavigate } from "react-router-dom";

function MealCard({ meal }) {
  const navigate = useNavigate();

  return (
    <div className="col-md-3 mb-4">
      <div
        className="card h-100"
        onClick={() => navigate(`/meal/${meal.idMeal}`)}
      >
        <img
          src={meal.strMealThumb}
          alt={meal.strMeal}
          className="card-img-top"
        />

        <div className="card-body">
          <h6>{meal.strMeal}</h6>
        </div>
      </div>
    </div>
  );
}

export default MealCard;