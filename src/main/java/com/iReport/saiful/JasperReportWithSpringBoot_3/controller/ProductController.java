  package com.iReport.saiful.JasperReportWithSpringBoot_3.controller;

  import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.ProductCustome;
  import com.iReport.saiful.JasperReportWithSpringBoot_3.exception.ProductNotFoundException;
  import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.ProductCustomeRepository;
  import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.ProductRepository;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.data.domain.PageRequest;
  import org.springframework.data.domain.Pageable;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductCustomeRepository productRepository;

	@PostMapping
	public ResponseEntity<ProductCustome> createProduct(@RequestBody ProductCustome productCustome) {
		return ResponseEntity.ok(productRepository.save(productCustome));
	}

	@GetMapping
	public ResponseEntity<List<ProductCustome>> getProductList() {
		return ResponseEntity.ok(productRepository.findAll());
	}

	@GetMapping("/{id}")

	public ResponseEntity<ProductCustome> findProduct(@PathVariable Integer id) {
		return ResponseEntity.ok(
				productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("ProductCustome Not found")));
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException() {
		return ResponseEntity.ok("ProductCustome Not Found");
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductCustome> updateProduct(@RequestBody ProductCustome productCustome) {
		ProductCustome p = productRepository.findById(productCustome.getId()).get();
		p.setName(productCustome.getName());
		p.setPrice(p.getPrice());

		p = productRepository.save(p);
		return ResponseEntity.ok(p);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok("Deleted");
	}

	@GetMapping("/pagination")
	public ResponseEntity<List<ProductCustome>> getProductPagination(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<ProductCustome> list = productRepository.findAll(pageable).getContent();
		return ResponseEntity.ok(list);

	}

	@GetMapping("/filter")
	public ResponseEntity<List<ProductCustome>> getProductPagination(@RequestParam String name) {
		return ResponseEntity.ok(productRepository.findByName(name));
	}

}
