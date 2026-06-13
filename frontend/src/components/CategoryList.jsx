function CategoryList({ categories, fetchCategoryMeals }) {
  return (
    <div className="mb-4">
      {categories.map((cat) => (
        <button
          key={cat}
          className="btn btn-outline-primary me-2 mb-2"
          onClick={() => fetchCategoryMeals(cat)}
        >
          {cat}
        </button>
      ))}
    </div>
  );
}

export default CategoryList;